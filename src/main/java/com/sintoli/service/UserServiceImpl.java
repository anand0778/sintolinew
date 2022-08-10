package com.sintoli.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sintoli.entity.User;
import com.sintoli.entity.UserModel;
import com.sintoli.exception.ItemAlreadyExistsException;
import com.sintoli.exception.ResourceNotFoundException;
import com.sintoli.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public User createUser(UserModel uModel) {
		if(userRepo.existsByEmail(uModel.getEmail())) {
			throw new ItemAlreadyExistsException("User is already "
					+ "registerd with email:"+uModel.getEmail());
		}
		User user=new User();
		BeanUtils.copyProperties(uModel,user);
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	@Override
	public User read(Long id) throws ResourceNotFoundException {
		
		return userRepo.findById(id).orElseThrow(()->new
				ResourceNotFoundException("User not found for the id:"+id));
		}
	@Override
	public User update(User user, Long id) throws ResourceNotFoundException{
		User oldUser=read(id);
		oldUser.setName(user.getName()!=null ? user.getName():oldUser.getName());
		oldUser.setEmail(user.getEmail()!=null ? user.getEmail():oldUser.getEmail());
		oldUser.setPassword(user.getPassword()!=null ? bcryptEncoder.encode(user.getPassword()):oldUser.getPassword());
		oldUser.setAge(user.getAge()!=null ? user.getAge():oldUser.getAge());
		return userRepo.save(oldUser);
	}
	@Override
	public void delete(Long id) throws ResourceNotFoundException{
	User user=read(id);
	userRepo.delete(user);
	}
}

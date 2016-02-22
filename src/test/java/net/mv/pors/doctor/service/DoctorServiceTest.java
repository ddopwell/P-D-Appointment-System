package net.mv.pors.doctor.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.mv.pors.doctor.dao.DoctorDao;
import net.mv.pors.doctor.domain.Doctor;
import net.mv.pors.doctor.dto.DoctorDto;
import net.mv.pors.role.dao.RoleDao;
import net.mv.pors.role.domain.Role;
import net.mv.pors.user.dao.UserDao;
import net.mv.pors.user.domain.User;
import net.mv.pors.user.dto.UserDto;


//Load Spring Context
//@ContextConfiguration("file:src/main/webapp/WEB-INF/config/spring-servlet.xml")
//@RunWith(SpringJUnit4ClassRunner.class)
public class DoctorServiceTest {
	
	
	@InjectMocks
	private DoctorServiceImpl doctorService;
	
	@Mock
	private DoctorDao doctorDaoMock;
	
	@Mock
	private UserDao userDaoMock;
	
	@Mock
	private RoleDao roleDaoMock;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void registerUser (){
		UserDto userDto = new UserDto();
		userDto.setUsername("ddopwell");
		userDto.setPassword("password");
		userDto.setRoleId(2l);
		
		User user = new User(userDto);
		
		Role userRole = roleDaoMock.getRole(userDto.getRoleId());
		user.setRole(userRole);
		userDaoMock.registerUser(user);
		//assertThat();
		//System.out.println(user.getUserId());
		//System.out.println(user.getUsername());
		assertThat(user.getUsername(),is("ddopwell"));
	}
	
	@Test
	public void storeDoctorInfo(){
		
		//UserDto userDto = new UserDto();
		//User user1 = new User();
		/*user1.setPassword(password);
		user1.setRole(role);
		user1.setUserId(userId);
		user1.setUsername(username);*/
		/*userDto.setClientId(1l);
		userDto.setId(2l);
		userDto.setRoleId(1l);
		userDto.setPassword(password);
		userDto.setUsername(username);
		userDto.setAuthenticated(true);*/
		
		
		DoctorDto doctorDto = new DoctorDto();
		
		doctorDto.setUsername("ddopwell");
		doctorDto.setDoctorId(1l);
		doctorDto.setFirstName("Destin");
		doctorDto.setLastName("Dopwell");
		doctorDto.setEmail("testemail@gmail.com");
		User user = userDaoMock.retrieveUserByUsername(doctorDto.getUsername());
		Doctor doctor = new Doctor(doctorDto);
		doctor.setUser(user);
		//User user1 = new User();
		//System.out.println(doctor);
		doctorDaoMock.saveDoctorInfo(doctor);
		assertThat(doctor.getUser(),is(user));
	}
	
	

}

package aspire.koy.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aspire.koy.dao.UserMapper;
import aspire.koy.pojo.User;
import aspire.koy.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserMapper userDao;
	
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

}

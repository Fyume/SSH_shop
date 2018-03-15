package zhku.jsj141.service.Impl;

import java.util.List;

import zhku.jsj141.dao.UserDao;
import zhku.jsj141.entity.user.Book;
import zhku.jsj141.entity.user.Favour;
import zhku.jsj141.entity.user.History;
import zhku.jsj141.entity.user.User;
import zhku.jsj141.entity.user.Work;
import zhku.jsj141.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public boolean add(User user){//增
		boolean rs= userDao.add(user);
		return rs;
	}
	@Override
	public String checkuid(User user){//查询用户id是否重复
		System.out.println("--service--");
		String name = "uid";
		List<User> list = userDao.select(user,name);
		if(list!=null&&!list.isEmpty()){
			return "用户id已存在";
		}
		System.out.println("该uid可用("+user.getUid()+")");
		return null;
	}
	@Override
	public String checkE(User user){//查询邮箱是否重复
		System.out.println("--service--");
		String name = "Email";
		List<User> list = userDao.select(user,name);
		if(list!=null&&!list.isEmpty()){
			return "邮箱已被绑定";
		}
		System.out.println("该E可用("+user.getEmail()+")");
		return null;
	}
	@Override
	public List<User> finds(User user,String name){//返回一个list
		List<User> list = userDao.select(user,name);
		return list;
	}
	@Override
	public boolean update(User user){
		boolean rs = userDao.update(user);
		return rs;
	}
	@Override
	public boolean delete(User user){
		boolean rs = userDao.delete(user);
		return rs;
	}
	//添加收藏
	@Override
	public boolean addF(Favour favour){
		boolean rs = userDao.addF(favour);
		return rs;
	}
	//取消收藏
	@Override
	public boolean delF(Favour favour){
		boolean rs = userDao.delF(favour);
		return rs;
	}
	//查找收藏记录
	@Override
	public List<Favour> findF(User user){
		List<Favour> list = userDao.findF(user);
		return list;
	}
	@Override
	public List<Favour> findF(User user,Book book){
		List<Favour> list = userDao.findF(user,book);
		return list;
	}
	@Override
	public List<Favour> findF(User user,Work work){//增
		List<Favour> list = userDao.findF(user,work);
		return list;
	}
	@Override
	public List<User> finds(User user,int status,int permission){//不定项条件查询
		List<User> list = null;
		if(status==2&&permission==2){
			list = userDao.findByIN(user);
		}else if(status==2&&permission!=2){
			list = userDao.findByINP(user);
		}else if(status!=2&&permission==2){
			list = userDao.findByINS(user);
		}else{
			list = userDao.findByINSP(user);//ID,name,status,permission
		}
		return list;
	}
	@Override
	public boolean addHistory(History history){//记录浏览历史
		boolean rs = userDao.addHistory(history);
		return rs;
	}
	//查找浏览历史
	@Override
	public List<History> findH(User user){
		List<History> list = userDao.findH(user);
		return list;
	}
	@Override
	public List<History> findH(User user,Book book){
		List<History> list = userDao.findH(user,book);
		return list;
	}
	@Override
	public List<History> findH(User user,Work work){//增
		List<History> list = userDao.findH(user,work);
		return list;
	}
}

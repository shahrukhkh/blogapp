package in.khan.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.khan.binding.Login;
import in.khan.binding.SignUp;
import in.khan.entity.UserDtlsEntity;
import in.khan.repo.UserDtlsEntityRepo;
import in.khan.utils.EncryptPwd;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDtlsEntityRepo dtlsEntityRepo;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private EncryptPwd encryptPwd;

	@Override
	public boolean saveUser(SignUp form) {
		UserDtlsEntity entity = new UserDtlsEntity();
		UserDtlsEntity user = dtlsEntityRepo.findByUserEmail(form.getUserEmail());

		if (user == null) {
			String userPwd = form.getUserPwd();
			
			try {
				String encryptedPwd = encryptPwd.encryptGivenPwd(userPwd);
				form.setUserPwd(encryptedPwd);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			BeanUtils.copyProperties(form, entity);

		
			dtlsEntityRepo.save(entity);
			return true;
		}
		return false;

	}

	@Override
	public boolean doLogin(Login form) {
		String userPwd = form.getUserPwd();
		try {
			String encryptedPwd = encryptPwd.encryptGivenPwd(userPwd);
			form.setUserPwd(encryptedPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserDtlsEntity user = dtlsEntityRepo.findByUserEmailAndUserPwd(form.getUserEmail(), form.getUserPwd());
		if (user == null) {
			return false;
		}
		httpSession.setAttribute("userId", user.getUserId());
		return true;
	}

	@Override
	public void doLogout() {
		httpSession.invalidate();

	}
}

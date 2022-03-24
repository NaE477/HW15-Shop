package services.impls;

import entities.users.Admin;
import repositories.interfaces.AdminRepository;
import services.interfaces.AdminService;

public class AdminServiceImpl extends BaseUserServiceImpl<Admin> implements AdminService {
    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }
}

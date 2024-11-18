package crm_app07buoi30.service;

import java.util.List;

import crm_app07buoi30.config.Roles;
import crm_app07buoi30.repository.RoleTableRepository;

public class RoleTableService {
	RoleTableRepository repository = new RoleTableRepository();
	
	public List<Roles> getRoles() {		
		return repository.findAllRoles();	
	}
	public int insertRoles(String nameRole,String descriptionRole) {
		return repository.insertRole(nameRole, descriptionRole);
	}
	public boolean checkingFilling(String nameRole,String descriptionRole) {
		if (nameRole!=null && !nameRole.isEmpty()
				&& descriptionRole != null && !descriptionRole.isEmpty() ) {
			return true;
		}
		return false;
	}
	public int deleteRoles(int roleId) {
		return repository.deleteRole(roleId);
	}
	
}
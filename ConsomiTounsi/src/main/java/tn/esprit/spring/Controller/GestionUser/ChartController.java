package tn.esprit.spring.Controller.GestionUser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tn.esprit.spring.Model.Chart.SexeC;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Service.GestionUser.ChartService;
import tn.esprit.spring.Service.GestionUser.UserService;

@Controller(value = "chartController")
@ELBeanName(value = "chartController")
@Join(path = "/ChartAdmin", to = "/Chart.jsf")
public class ChartController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository UserRepository;
	@Autowired
	ChartService chartService;
	protected List<SexeC> sexes ;
	
	public void getUserSelonSexe()
	{
		sexes = new ArrayList<SexeC>();
		
		sexes.add(new SexeC("Homme",userService.getNombresUsersSelonSexe("HOMME")));
		sexes.add(new SexeC("Femme",userService.getNombresUsersSelonSexe("FEMME")));

		//System.out.println(userService.getNombresUsersSelonSexe("HOMME"));
		
	}
	public int getNombrUsers()
	{
		return UserRepository.NombreUsers();
	}
	public Long getNombrOrders()
	{
		return chartService.getNombreOrders();
	}
	public float getTotalSales()
	{
		return chartService.getTotalSales();
	}
	public float getTotalDonation()
	{
		return chartService.getTotalDonation();
	}
	

	


	public List<SexeC> getSexes() {
		return sexes;
	}

	public void setSexes(List<SexeC> sexes) {
		this.sexes = sexes;
	}
	
	
	
	
}

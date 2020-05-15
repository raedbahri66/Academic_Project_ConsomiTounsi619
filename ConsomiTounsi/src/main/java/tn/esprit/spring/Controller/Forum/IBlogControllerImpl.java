package tn.esprit.spring.Controller.Forum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.Forum.Sujet;
import tn.esprit.spring.Service.Forum.ICategorieSujetService;
import tn.esprit.spring.Service.Forum.ISujetService;
import tn.esprit.spring.Service.Forum.IVoteSujetService;

@Controller(value = "blogController")
@ELBeanName(value = "blogController")
@Join(path = "/blog", to = "/blog.jsf")
@SessionScope
public class IBlogControllerImpl{
	@Autowired
	ISujetService iSujetService;
	@Autowired
	 ICategorieSujetService  icategorieSujetService;
	@Autowired 
	IVoteSujetService iVoteSujetService;
	private RepeatPaginator paginator;
	private Long id;
	private String nomSujet;
	private String description;
	@Temporal (TemporalType.DATE)
    private Date dateAjout;
	private int nbVue;
	private int nbLike;
	private int nbDislike;
	private int nbpoint;
	private List<Sujet> sujets;
	private Sujet sujet;
	private Sujet sujetrec;
	User idUser;
	CategorieSujet idCategorieSujet;
	
	public CategorieSujet getIdCategorieSujet() {
		return idCategorieSujet;
	}
	public void setIdCategorieSujet(CategorieSujet categorieSujet) {
		idCategorieSujet = categorieSujet;
	}
	public User getIdUser() {
		return idUser;
	}
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomSujet() {
		return nomSujet;
	}
	public void setNomSujet(String nomSujet) {
		this.nomSujet = nomSujet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public int getNbVue() {
		return nbVue;
	}
	public void setNbVue(int nbVue) {
		this.nbVue = nbVue;
	}
	public int getNbLike() {
		return nbLike;
	}
	public void setNbLike(int nbLike) {
		this.nbLike = nbLike;
	}
	public int getNbDislike() {
		return nbDislike;
	}
	public void setNbDislike(int nbDislike) {
		this.nbDislike = nbDislike;
	}
	public int getNbpoint() {
		return nbpoint;
	}
	public void setNbpoint(int nbpoint) {
		this.nbpoint = nbpoint;
	}
	
	/********show all sujets****/
	public List<Sujet> getAllSujets() {
		List<Sujet> sujets =iSujetService.getAllSujets();
		for (Sujet s:sujets){
			iVoteSujetService.affecterdespoints(s.getId());
		}
		
		return iSujetService.getAllSujets();
	}
	
	@PostConstruct
	public void init(){
	List <Sujet> s= getAllSujets();
	paginator = new RepeatPaginator(s);
}

    public RepeatPaginator getPaginator() {
    	 
        return paginator;
    }
	/******shoow one sujet******/
	public Sujet getSujet() {
		return sujet;
	
	}
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}
	
  String a;
	private String getCountryFromJSF(FacesContext context) {
        Map<String, String> parameters = context.getExternalContext().getRequestParameterMap();
        return parameters.get("idsujet");
    }
	 public Long outcome() {
	        FacesContext context = FacesContext.getCurrentInstance();
	        a = getCountryFromJSF(context);
	        System.out.println(a);
	        return Long.parseLong(a);
	        
	    }
	
	public String convertireDate(Date D){
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
		return formatter.format(D);
	}
	public String convertireTime(Date d){
	SimpleDateFormat  formatter = new SimpleDateFormat(" MMMM d, yyyy 'at' HH:mm a "); 
	return formatter.format(d);
}
	public Sujet getSujetrec() {
		return sujetrec;
	}
	public Sujet findSujetrec() {
		return sujetrec= iSujetService.findOne(outcome());
	}
	public void setSujetrec(Sujet sujetrec) {
		this.sujetrec = sujetrec;
	}
	
	

}
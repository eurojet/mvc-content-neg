package rewardsonline.accounts;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A controller for obtaining login names. This is for demo purposes only - you
 * would never do this in a real application.
 */
@Controller
public class SystemController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @RequestMapping(value={"/",""})
    public String indexHtml() {
        return "welcome";
    }
   
    @RequestMapping(value={"/","","/login"}, 
            headers = "Accept=application/hal+json, application/json")
    public String index() {
        return "redirect:/rest";
    }
    
    @RequestMapping(value={"/rest","/browser"})
    public ResponseEntity indexRestHtml() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", "/browser/index.html");
        return new ResponseEntity<Void>(headers, HttpStatus.FOUND);
    }

    @RequestMapping(value="/rest", 
            headers = "Accept=application/hal+json, application/json")
    public String indexRest(Model model, HttpServletRequest request) {
        List<String> urlUsers = new ArrayList<String>();
        String str = request.getRequestURL().substring(7);
        String[] aux = str.split("@");
        if (aux.length>1) str = aux[1];
        
        login(model);
        for (Object username : (List)model.asMap().get("users")) {
            String s = "http://"+username+":"+username+"@"+str+"/user/"+username;
            urlUsers.add(s);
        }
        
        model.addAttribute("urlUsers", urlUsers);
        model.addAttribute("url", request.getRequestURL());
        return "restwelcome";
    }

    @RequestMapping(value="/rest/user", 
            headers = "Accept=application/hal+json, application/json")
    public String restUser(Principal principal) {
        if (principal == null)
            throw new AccessDeniedException("Not authenitcated");
        return "redirect:/rest/user/"+principal.getName();
    }
    
    /**
     * Handles requests to list all available usernames.
     */
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(Model model) {
        List<String> users = jdbcTemplate.query("SELECT username FROM T_Customer", 
            new RowMapper<String>() {
                public String mapRow(ResultSet rs, int row) throws SQLException {
                    return rs.getString("username");
                }
            }
        );
        
        model.addAttribute("users", users);
        return "login";
    }
}

package rewardsonline;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 Registering it in the spring config using the namespace configuration:

 <mvc:interceptors>
    <beans:bean class="ViewNameInModelInterceptor" />
 </mvc:interceptors>

*/
public class ViewNameInModelInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
            Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            String view = modelAndView.getViewName();
            modelAndView.addObject("springViewName", view);
            modelAndView.addObject("springViewNameKey", view.replaceAll("/","."));
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}


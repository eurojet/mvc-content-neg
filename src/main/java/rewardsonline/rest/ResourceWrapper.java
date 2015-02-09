package rewardsonline.rest;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


public class ResourceWrapper<T> extends ResourceSupport {
    @JsonProperty
    @JsonUnwrapped 
    private T wrapped;
    
    private Resources<List> _embedded;


    public ResourceWrapper(T object) {
        this.wrapped = object;
    }
    
    public void setResources(Resources<List> embedded) {
        this._embedded = embedded;
    }
}

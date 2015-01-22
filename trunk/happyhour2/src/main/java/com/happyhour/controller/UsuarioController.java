package com.happyhour.controller;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.happyhour.entity.Authority;
import com.happyhour.entity.Usuario;
import com.happyhour.service.AuthorityService;
import com.happyhour.service.UsuarioService;

@RequestMapping("/usuarios")
@Controller
@RooWebScaffold(path = "usuarios", formBackingObject = Usuario.class)
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
	
    @Autowired
    private AuthorityService authorityService;
	
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Usuario usuario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usuario);
            return "usuarios/create";
        }
        uiModel.asMap().clear();
        usuario.persist();
        return "redirect:/usuarios/" + encodeUrlPathSegment(usuario.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Usuario());
        return "usuarios/create";
    }

    @RequestMapping(value = "/show", produces = "text/html")
    public String show(@RequestParam("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        uiModel.addAttribute("usuario", Usuario.findUsuario(id));
        uiModel.addAttribute("itemId", id);
        return "usuarios/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("usuarios", Usuario.findUsuarioEntries(firstResult, sizeNo));
            float nrOfPages = (float) Usuario.countUsuarios() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());
        }
        return "usuarios/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Usuario usuario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usuario);
            return "usuarios/update";
        }
        uiModel.asMap().clear();
        usuario.merge();
        return "redirect:/usuarios/" + encodeUrlPathSegment(usuario.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Usuario.findUsuario(id));
        return "usuarios/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Usuario usuario = Usuario.findUsuario(id);
        usuario.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/usuarios";
    }

    void populateEditForm(Model uiModel, Usuario usuario) {
        uiModel.addAttribute("usuario", usuario);
        uiModel.addAttribute("authoritys", Authority.findAllAuthoritys());
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }
    
    @RequestMapping(value = "/list", produces = "text/html")
    private String redirectTo(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size,Model uiModel){
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            List<Usuario> usuariosList = Usuario.findUsuarioEntries(firstResult, sizeNo);
            uiModel.addAttribute("usuarios", usuariosList);
            
            Long countUsuarios = Usuario.countUsuarios();
            float nrOfPages = (float)  countUsuarios / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
        	List<Usuario> usuariosList = Usuario.findAllUsuarios();
            uiModel.addAttribute("usuarios", usuariosList);
        }    	
    	
    	return "usuarios/list";
    }
    @RequestMapping(value="new-user-bootstrap",produces="text/html")
    private String newUser(Model uiModel){
        populateEditForm(uiModel, new Usuario());
        
        List<Authority> authorityList = authorityService.findAllAuthoritys();
        List<LabelValue> list = new ArrayList<LabelValue>();
        
        for (Authority authority : authorityList) {
        	LabelValue lv = new LabelValue();
        	lv.setLable(authority.getRoleName());
        	lv.setValue(authority.getId());
        	list.add(lv);
		}
        
        uiModel.addAttribute("authorityList", list);
        
        return "usuarios/create";
    }
    
    @RequestMapping(value = "/createUsuario",method = RequestMethod.POST, produces = "text/html")
    public String createUsuario(@Valid Usuario usuario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usuario);
            
            List<Authority> authorityList = authorityService.findAllAuthoritys();
            List<LabelValue> list = new ArrayList<LabelValue>();
            
            for (Authority authority : authorityList) {
            	LabelValue lv = new LabelValue();
            	lv.setLable(authority.getRoleName());
            	lv.setValue(authority.getId());
            	list.add(lv);
    		}
            
            uiModel.addAttribute("authorityList", list);
            
            return "usuarios/create";
        }
        uiModel.asMap().clear();
        
        Authority auth =  authorityService.findAuthority(new Long(usuario.getAuthorityFormValue()));
        usuario.getRolesList().add(auth);
        usuario.persist();
        return "redirect:/usuarios/list";
    }
    class LabelValue{
    	
    	 private String label;
    	 private Long value;
    	 
		public String getLabel() {
			return label;
		}
		public void setLable(String label) {
			this.label = label;
		}
		public Long getValue() {
			return value;
		}
		public void setValue(Long value) {
			this.value = value;
		}    	
    	 
    }
    
}

package com.xo.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.xo.VO.SmarteditorVO;
import com.xo.VO.boardVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String insertForm(Locale locale,Model model,@ModelAttribute ("boardVO") boardVO vo) {
		
		return "";
	}
	
	@RequestMapping(value="/singleImageUploader.do")
	public String simpleImageUploader(
		HttpServletRequest req, SmarteditorVO smarteditorVO) 
        	throws UnsupportedEncodingException{
	String callback = smarteditorVO.getCallback();
	String callback_func = smarteditorVO.getCallback_func();
	String file_result = "";
	String result = "";
	MultipartFile multiFile = smarteditorVO.getFiledata();
	try{
		if(multiFile != null && multiFile.getSize() > 0 && 
        		StringUtils.isNotBlank(multiFile.getName())){
			if(multiFile.getContentType().toLowerCase().startsWith("image/")){
            	String oriName = multiFile.getName();
                String uploadPath = req.getServletContext().getRealPath("/img");
                String path = uploadPath + "/smarteditor/";
                File file = new File(path);
                if(!file.exists()){
                file.mkdirs();
                }
                String fileName = UUID.randomUUID().toString();
                smarteditorVO.getFiledata().transferTo(new File(path + fileName));
                file_result += "&bNewLine=true&sFileName=" + oriName + 
                			   "&sFileURL=/img/smarteditor/" + fileName;
			}else{
				file_result += "&errstr=error";
			}
		}else{
			file_result += "&errstr=error";
		}
	} catch (Exception e){
		e.printStackTrace();
	}
	result = "redirect:" + callback + 
			 "?callback_func=" + URLEncoder.encode(callback_func,"UTF-8") + file_result;
	return result;
}
	
	
	
	
	
	
}

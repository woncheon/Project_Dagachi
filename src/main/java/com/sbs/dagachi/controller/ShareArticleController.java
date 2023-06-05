package com.sbs.dagachi.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sbs.dagachi.service.ShareArticleService;
import com.sbs.dagachi.vo.Member;
import com.sbs.dagachi.vo.ShareArticle;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShareArticleController {

	public ShareArticleService shareArticleService;

	public ShareArticleController(ShareArticleService shareArticleService) {
		this.shareArticleService = shareArticleService;
		

	}

	@RequestMapping("/article/list")
	
	public String showBoard2(HttpSession session,
			Model model,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "article_title, article_register, article_regdate") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword){
		
		
		int articleCount = shareArticleService.getArticleboardId2Count(searchKeywordTypeCode, searchKeyword);
		int itemsCountInAPage = 10;
		int pagesCount = (int) Math.ceil((double) articleCount / itemsCountInAPage);
		
		List<ShareArticle> borad2 = shareArticleService.getBoardId2(searchKeywordTypeCode, searchKeyword, itemsCountInAPage, page);
		
		
		
		model.addAttribute("borad2",borad2);
		model.addAttribute("articleCount",articleCount);
		model.addAttribute("page",page);
		model.addAttribute("pagesCount",pagesCount);
		
		return "/article/list";
		
	
	}
	
	@RequestMapping("/article/detail")
	public String showDetail(HttpSession session,Model model,@RequestParam("article_id") String article_id) {
		
		List<ShareArticle> board = shareArticleService.getarticlebyarticleid(article_id);
	
		model.addAttribute("board",board);
		
		return "/article/detail";
		
	}
	
	@RequestMapping("/article/domodify")
	public String showmodify(HttpSession session,Model model,@RequestParam("article_id") String article_id) {
		
		List<ShareArticle> board = shareArticleService.getarticlebyarticleid(article_id);
		
		model.addAttribute("board",board);
		return "/article/modifyForm";
	}
	
	@RequestMapping(value = "/article/modify", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String articleModify(HttpSession session,@RequestParam("article_title")String article_title,
								@RequestParam("article_body")String article_body,
								@RequestParam(value = "article_attach", required = false)String article_attach,
								@RequestParam(value= "article_id", required = false)String article_id) {
		shareArticleService.articleModify(article_title, article_body, article_attach, article_id);
		

		
		return "redirect:/article/detail?article_id="+article_id;
	   
	}
	
	@RequestMapping(value = "/article/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteArticle(@RequestParam("article_id") String articleId) {
		shareArticleService.articleDelete(articleId);
	    return "/article/list";
	}
	
	@RequestMapping("/article/write")
	@ResponseBody
	public String writeArticle(HttpSession session, @RequestParam("article_title") String article_title,
								@RequestParam("article_body") String article_body,
								@RequestParam(value = "article_attach", required = false) MultipartFile article_attach) {
		Member member = (Member) session.getAttribute("loginUser");
		
		  String filename = null;
		    if (article_attach != null && !article_attach.isEmpty()) {
		        try {
		            filename = article_attach.getOriginalFilename();
		            String filePath = "c:/upload/file/" + filename;
		            article_attach.transferTo(new File(filePath));
		           
		        } catch (IOException e) {
		           
		            e.printStackTrace();
		        }
		    } else {}
		       
		    shareArticleService.articeWrite(article_title, article_body, filename, member.getMember_id());
		
		return "/article/list";
	}
	@RequestMapping("/article/insert")
	public String insertArticle() {
		return "/article/writeForm";
	}


	

}

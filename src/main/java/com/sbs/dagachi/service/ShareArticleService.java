package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.ShareArticleRepository;
import com.sbs.dagachi.vo.ShareArticle;



@Service
public class ShareArticleService {
	private ShareArticleRepository shareArticleRepository;
	
	public ShareArticleService(ShareArticleRepository shareArticleRepository) {
		this.shareArticleRepository = shareArticleRepository;
		
	}
	
	public List<ShareArticle> getBoardId2(String searchKeywordTypeCode, String searchKeyword, int itemsCountInAPage, int page){
		int limitStart = (page - 1) * itemsCountInAPage;
	    int limitTake = itemsCountInAPage;
	    
	    List<ShareArticle> articles = shareArticleRepository.getBoardId2(limitStart, limitTake, searchKeywordTypeCode, searchKeyword);
	    
	    return articles;
	}
	
	public int getArticleboardId2Count(
			String searchKeywordTypeCode, String searchKeyword) {
		return shareArticleRepository.getArticleboardId2Count( searchKeywordTypeCode, searchKeyword);
	}
	
	public List<ShareArticle> getarticlebyarticleid(String article_id){
		return shareArticleRepository.getarticlebyarticleid(article_id);
	}
	
	public void articleModify(String article_title,String article_body,String article_attach,String article_id) {
		
		shareArticleRepository.articleModify(article_title, article_body ,article_attach, article_id);
		
	}
	
	public void articleDelete(String article_id) {
		shareArticleRepository.articleDelete(article_id);
	}
	
	public void articeWrite(String artice_title, String artice_body, String artice_attach, String artice_register) {
		shareArticleRepository.articleWrite(artice_title, artice_body, artice_attach, artice_register);
	}
	
	
}
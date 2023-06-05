package com.sbs.dagachi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.dagachi.repository.ArticleRepository;
import com.sbs.dagachi.vo.Article;

@Service
public class ArticleService {
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		
	}
	
	public List<Article> getBoardId1(String searchKeywordTypeCode, String searchKeyword, int itemsCountInAPage, int page){
		int limitStart = (page - 1) * itemsCountInAPage;
	    int limitTake = itemsCountInAPage;
	    
	    List<Article> articles = articleRepository.getBoardId1(limitStart, limitTake, searchKeywordTypeCode, searchKeyword);
	    
	    return articles;
	}
	
	public int getArticleboardId1Count(
			String searchKeywordTypeCode, String searchKeyword) {
		return articleRepository.getArticleboardId1Count( searchKeywordTypeCode, searchKeyword);
	}
	
	public List<Article> getarticlebyarticleid(String article_id){
		return articleRepository.getarticlebyarticleid(article_id);
	}
	
	
	
	public void articleModify(String article_title,String article_body,String article_attah,String article_id ) {
		articleRepository.articleModify(article_title, article_body, article_attah, article_id);
	}
	
	public void articleDelete(String article_id) {
		articleRepository.articleDelete(article_id);
	}
	
	 public void articleInsert(String article_register, String article_title, String article_body, String filename, String article_important, String fileSize, String fileMimeType) {
	        articleRepository.articleInsert(article_register, article_title, article_body, filename, article_important, fileSize, fileMimeType);
	    }

	public Article getArticleById(int articleId) {
	    
	    Article article = new Article();
	    article.setArticle_id(articleId);
	    article.setArticle_title("Sample Article");
	    article.setArticle_body("This is a sample article.");
	    
	    
	    String filePath = "c:/article/file/upload/" + articleId + ".pdf";
	    article.setArticle_attach(filePath);

	    return article;
	}
	
	


	 }

	
	

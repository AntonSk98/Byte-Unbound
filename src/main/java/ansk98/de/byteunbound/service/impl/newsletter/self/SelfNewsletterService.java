package ansk98.de.byteunbound.service.impl.newsletter.self;

import ansk98.de.byteunbound.domain.Article;
import ansk98.de.byteunbound.repository.IArticleRepository;
import ansk98.de.byteunbound.service.api.newsletter.ISelfNewsletterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class SelfNewsletterService implements ISelfNewsletterService {

    private final IArticleRepository articleRepository;

    public SelfNewsletterService(IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional
    public void addArticle(Article.Builder article) {
        articleRepository.save(article.build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getNewsletter() {
        return articleRepository.findByPublishedFalse();
    }

    @Override
    @Transactional
    public void markAsPublishedNewsletter() {
        List<Article> newsletter = getNewsletter();
        newsletter.forEach(Article::publish);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getNewsletterPublishedAfter(ZonedDateTime dateTime) {
        return articleRepository.findByPublishedTrueAndPublishedAtAfter(dateTime);
    }


}

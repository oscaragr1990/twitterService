package monitor.twitter.servicio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Configuration
@PropertySource("classpath:/config/twitter.propierties")
public class TwitterConfig {
	@Autowired
	private Environment env;

	@Bean("twitterSource")
	public Twitter twitterSource() {
		String consumerSecret = env.getProperty("authConsumer.consumerSecret");
		String consumerKey = env.getProperty("authConsumer.consumerKey");
		String tokenSecret = env.getProperty("accessToken.tokenSecret");
		String token = env.getProperty("accessToken.token");
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(token, tokenSecret));
		return twitter;
	}

}
package com.elypia.elypiai.myanimelist;

import com.elypia.elypiai.ElyUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Anime {

	private int id;
	private String title;
	private String englishTitle;
	private int episodes;
	private double score;
	private String type;
	private String status;
	private String startDate;
	private String endDate;
	private String synopsis;
	private String image;

	public Anime(Document document) {
		Element element = document.getElementsByTag("anime").first().getElementsByTag("entry").first();

		id = ElyUtils.optInt(element.getElementsByTag("id").first().text(), -1);
		title = element.getElementsByTag("title").first().text();
		englishTitle = element.getElementsByTag("english").first().text();
		episodes = ElyUtils.optInt(element.getElementsByTag("episodes").first().text(), -1);
		score = ElyUtils.parseDoubleOrDefault(element.getElementsByTag("score").first().text(), -1);
		type = element.getElementsByTag("type").first().text();
		status = element.getElementsByTag("status").first().text();
		startDate = element.getElementsByTag("start_date").first().text();
		endDate = element.getElementsByTag("end_date").first().text();

		if (endDate.equals("0000-00-00"))
			endDate = null;

		synopsis = element.getElementsByTag("synopsis").first().text();

		image = element.getElementsByTag("image").first().text();
	}

	/**
	 * @return	The id of the anime on MyAnimeList or -1 if for
	 * 			whatever reason the id couldn't be parsed.
	 */

	public int getId() {
		return id;
	}

	/**
	 * @return	The original title of the anime.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return	The English title for the anime.
	 */

	public String getEnglishTitle() {
		return englishTitle;
	}

	/**
	 * @return	The number of episodes.
	 */

	public int getNumOfEpisodes() {
		return episodes;
	}

	/**
	 * @return	The MyAnimeList rating of the anime.
	 */

	public double getScore() {
		return score;
	}

	/**
	 * @return	The type of media this is, for example
	 * 			TV, Special or Movie.
	 */

	public String getType() {
		return type;
	}

	/**
	 * @return	The running status of the show, for example
	 * 			Finished Airing, Currently Airing
	 */

	public String getStatus() {
		return status;
	}

	/**
	 * @return	The date the anime started airing.
	 */

	public String getStartDate() {
		return startDate;
	}

	/**
	 * @return	The date the anime finished airing, or null
	 * 			if the animing is still ongoing.
	 */

	public String getEndDate() {
		return endDate;
	}

	/**
	 * @return	The synopsis for the anime.
	 */

	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * @return	The url to the image of the anime.
	 */

	public String getImageUrl() {
		return image;
	}

	/**
	 * Downloads the cover image of this anime from the
	 * url obtained for manipulating or caching.
	 *
	 * @return	The downloaded image as a BufferedImage.
	 */

	public BufferedImage getImage() {
		try {
			return ImageIO.read(new URL(image));
		} catch (IOException ex) {
			return null;
		}
	}
}

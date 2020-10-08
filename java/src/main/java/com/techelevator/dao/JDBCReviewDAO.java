package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Review;

@Component
public class JDBCReviewDAO implements ReviewDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public void addReview(Review review) {
		String sql = "INSERT INTO review (title, overall, color, taste, head, smell, beerId, rating, userId, reviewImgUrl, reviewDate, breweryId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbc.update(sql, review.title, review.overall, review.color, review.taste, review.head, review.smell, review.beerId
				, review.rating, review.userId, review.reviewImgUrl, review.reviewDate, review.breweryId);
	}

	@Override
	public void deleteReview(Review review) {
		String sql = "DELETE * FROM review WHERE id = ?";
		jdbc.update(sql, review.id);
		
	}

	@Override
	public List<Review> getReviewsByBeer(Long BeerId) {
		List<Review> reviewsByBeer = new ArrayList<Review>();
		String sql = "SELECT * FROM review WHERE beerId = ?";
		SqlRowSet result = jdbc.queryForRowSet(sql, BeerId);
		while (result.next()) {
			Review review = mapToReview(result);
			reviewsByBeer.add(review);
		}
		return reviewsByBeer;
	}

	@Override
	public List<Review> getReviewsByBrewery(Long BreweryId) {
		List<Review> reviewsByBrewery = new ArrayList<Review>();
		String sql = "SELECT * FROM review WHERE breweryId = ?";
		SqlRowSet result = jdbc.queryForRowSet(sql, BreweryId);
		while (result.next()) {
			Review review = mapToReview(result);
			reviewsByBrewery.add(review);
		}
		return reviewsByBrewery;
	}
	
	@Override
	public List<Review> getReviewsByUserId(Long userId) {
		List<Review> reviewsByUser = new ArrayList<Review>();
		String sql = "SELECT * FROM review WHERE userId = ?";
		SqlRowSet result = jdbc.queryForRowSet(sql, userId);
		while (result.next()) {
			Review review = mapToReview(result);
			reviewsByUser.add(review);
		}
		return reviewsByUser;
	}
	
	
	
	
	public Review mapToReview(SqlRowSet row){
		Review review = new Review();
		review.id = row.getLong("id");
		review.title = row.getString("title");
		review.overall = row.getString("overall");
		review.color = row.getString("color");
		review.taste = row.getString("taste");
		review.head = row.getString("head");
		review.smell = row.getString("smell");
		review.beerId = row.getLong("beerId");
		review.rating = row.getInt("rating");
		review.userId = row.getLong("userId");
		review.reviewImgUrl = row.getString("reviewImgUrl");
		review.reviewDate = row.getDate("reviewDate");
		review.breweryId = row.getLong("breweryId");
		
		return review;
	}



}

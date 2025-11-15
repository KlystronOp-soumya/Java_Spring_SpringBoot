package com.demo.quarkus.jpa.artist.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Random;

import javax.sql.DataSource;

import com.demo.quarkus.jpa.artist.Artist;
import com.google.common.escape.Escaper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArtistRepository {

	private DataSource dataSource;

	public ArtistRepository(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void persist(Artist artist) {
		final String sql = "INSERT INTO t_artists (id,name,bio,created_date) VALUES (?, ?, ?, ?)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			artist.setId(Math.abs(new Random().nextLong()));

			ps.setLong(1, artist.getId());
			ps.setString(2, artist.getName());
			ps.setString(3, artist.getBio());
			ps.setTimestamp(4, Timestamp.from(artist.getCreatedAt()));
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public Artist findById(long id) {

		final String sql = "SELECT id,name,bio,created_date FROM t_artists  WHERE id = ?";
		Artist artist = new Artist();

		try (Connection connection = this.dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setLong(1, id);

			ResultSet rSet = ps.executeQuery();
			if (rSet.next()) {
                System.out.println("Data Present");
				artist.setId(rSet.getLong("id"));
				artist.setName(rSet.getString("name"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return artist;
	}

}

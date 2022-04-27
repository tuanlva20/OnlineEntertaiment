package model;

import java.io.Serializable;
import javax.persistence.*;

import constant.NameParams;
import constant.NameStored;

import java.util.List;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = NameStored.FIND_USERS_LIKED_VIDEO_BY_VIDEOHREF,
			procedureName = "sp_selectUsersLikedVideoByHref",
			resultClasses = Users.class,
			parameters = @StoredProcedureParameter(name=NameParams.Video_Href, type=String.class))
})
@Entity
@Table(name = "Users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM Users u")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String username;

	@Column(name = "Admin")
	private boolean admin;

	@Column(name = "Email")
	private String email;

	@Column(name = "Fullname")
	private String fullname;

	@Column(name = "Password")
	private String password;

	// bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;

	// bi-directional many-to-one association to Share
	@OneToMany(mappedBy = "user")
	private List<Share> shares;

	public Users() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setUser(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setUser(null);

		return favorite;
	}

	public List<Share> getShares() {
		return this.shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public Share addShare(Share share) {
		getShares().add(share);
		share.setUser(this);

		return share;
	}

	public Share removeShare(Share share) {
		getShares().remove(share);
		share.setUser(null);

		return share;
	}

}
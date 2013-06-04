package example.domain.shared.security;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Transient;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Objects;

import example.ddd.AbstractAggregateEntity;
import example.ddd.DomainEntity;

@DomainEntity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Account extends AbstractAggregateEntity {

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password = "";

	@Column(nullable = false)
	private Boolean enabled = Boolean.TRUE;

	@Column(nullable = false)
	private String displayName;

	@Transient
	@Autowired
	private PasswordEncoder passwordEncoder;

	protected Account() {
	}

	public Account(String username, String displayName) {
		this.username = checkNotNull(username);
		this.displayName = checkNotNull(displayName);
	}

	public void disable() {
		enabled = Boolean.FALSE;
	}

	public void enable() {
		enabled = Boolean.TRUE;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void updatePassword(String rawPassword) {
		checkNotNull(rawPassword);

		password = passwordEncoder.encode(rawPassword);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(username).toString();
	}

}

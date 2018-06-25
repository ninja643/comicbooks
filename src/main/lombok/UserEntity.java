

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Value;

@XmlRootElement
@Value
@Table
@Entity
public class UserEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Version
	private int version;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String email;
}

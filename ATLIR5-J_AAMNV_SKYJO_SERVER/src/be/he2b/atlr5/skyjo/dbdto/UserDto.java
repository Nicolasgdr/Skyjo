package be.he2b.atlr5.skyjo.dbdto;

import be.he2b.atlr5.skyjo.exception.DtoException;
import java.util.Objects;
/**
 *
 * @author mad8
 */
public class UserDto extends Dto<Integer>{
    private String mail;

    /**
     * constructeur d'un user persistant
     *
     * @param id
     * @param mail
     * @throws be.he2b.atlr5.skyjo.exception.DtoException
     */
    public UserDto(Integer id, String mail) throws DtoException {
       super(id);
       this.mail = Objects.requireNonNull(mail,"Mail must not be null");
        
    } 

    /**
     *
     * @return mail
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[UserDto] (" + getKey()+ ") " + getMail();
    }
}

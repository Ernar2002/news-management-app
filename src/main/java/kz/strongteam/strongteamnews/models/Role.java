//package kz.strongteam.strongteamnews.models;
//
//import javax.persistence.*;
//import kz.strongteam.strongteamnews.models.enums.ERole;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
////import org.springframework.security.core.GrantedAuthority;
//
//@Data
//@Entity
//@Table(name = "roles")
//@AllArgsConstructor
//@NoArgsConstructor
//public class Role extends BaseModel implements GrantedAuthority {
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "name")
//    private ERole name = ERole.ROLE_USER;
//
//    @Override
//    public String getAuthority() {
//        return this.name.toString();
//    }
//}
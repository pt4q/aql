package pl.com.pt4q.product_manager.modules.environment.data.pack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.pt4q.product_manager.modules.environment.data.master.EnvMasterEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class EnvPackagingEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    private EnvMasterEntity master;

}

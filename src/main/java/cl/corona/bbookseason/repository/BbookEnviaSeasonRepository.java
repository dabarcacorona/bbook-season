package cl.corona.bbookseason.repository;

import cl.corona.bbookseason.model.BbookEnviaSeason;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BbookEnviaSeasonRepository extends CrudRepository<BbookEnviaSeason, String> {

    public List<BbookEnviaSeason> findAllByTranType(String tranType);

    @Modifying(clearAutomatically = true)
    @Query(value = "update app_sam.sdi_sdibasatr s set s.download_date_1 = sysdate where rowid = :id", nativeQuery = true)
    public void updSdiSdibasatr(@Param("id") String id);
}

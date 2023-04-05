package cl.corona.bbookseason.services;

import cl.corona.bbookseason.model.BbookEnviaSeason;
import cl.corona.bbookseason.model.JsonCab;
import cl.corona.bbookseason.model.JsonDet;
import cl.corona.bbookseason.repository.BbookEnviaSeasonRepository;
import cl.corona.bbookseason.utilities.Utility;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SeasonService {

    @Value("${api_url}")
    private String apiurl;

    @Value("${token_uri}")
    private String token;

    @Autowired
    BbookEnviaSeasonRepository updsdirepository;

    private static final Logger LOG = LoggerFactory.getLogger(SeasonService.class);

    public void EnvioDimens(List<BbookEnviaSeason> bbookbrd, String tipo) {

        Gson g = new Gson();
        JsonCab jsoncab = null;
        List<JsonDet> json = new ArrayList<>();
        HttpEntity<String> entity = null;

        try{

            for (BbookEnviaSeason row : bbookbrd) {

                json.add(new JsonDet(row.getAtrCode(), row.getAtrCodeDesc(), row.getInactive()));
            }

            jsoncab = new JsonCab(json);

            HttpHeaders headers = new HttpHeaders();
            headers.set("user-agent", "Application");

            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-Bbook-Token", token);

            entity = new HttpEntity<String>(g.toJson(jsoncab), headers);

        }catch (Exception e) {

            LOG.error("Error al cargar json", e);

        }

        String responses = null;

        try {

            responses = Utility.BulkUpdateApiPoster(entity, apiurl, tipo);

        }catch (Exception e) {

            LOG.error("Error al enviar datos", e);

            responses = "No se pudo informar Marca";
        }

        for (BbookEnviaSeason row : bbookbrd) {
            updsdirepository.updSdiSdibasatr(row.getId());
        }
    }
}

package cl.corona.bbookseason.scheduler;

import cl.corona.bbookseason.model.BbookEnviaSeason;
import cl.corona.bbookseason.repository.BbookEnviaSeasonRepository;
import cl.corona.bbookseason.services.SeasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private SeasonService dimensService;

    private BbookEnviaSeasonRepository bbookenvioseasonrepository;


    @Autowired
    public ScheduledTasks(BbookEnviaSeasonRepository bbookenvioseasonrepository) {
        this.bbookenvioseasonrepository = bbookenvioseasonrepository;
    }

    @Scheduled(cron = "${cron.expression}")
    public void scheduledBbook() throws InterruptedException {
        LOG.info("{} : Generacion periodica para el envio de Colores  - {}",
                dateTimeFormatter.format(LocalDateTime.now()));

        // Date exceptions
        LocalDate today = LocalDate.now();
        int count = 0;
        List<BbookEnviaSeason> Bbookdimens = new ArrayList<>();

        try {
            String statusBrand = "T";
            Bbookdimens = (List<BbookEnviaSeason>) bbookenvioseasonrepository.findAllByTranType("A");

        } catch (InvalidDataAccessResourceUsageException e) {
            LOG.error("{}: Ocurrio un error al momento de rescatar las Marcas: ", e);
            return;
        }

        LOG.info("Cantidad de Marcas para generar: {}", Bbookdimens.size());

        if (Bbookdimens.size() > 0) {
            dimensService.EnvioDimens(Bbookdimens, "A");
        }

        try {
            String statusBrand = "T";
            Bbookdimens = (List<BbookEnviaSeason>) bbookenvioseasonrepository.findAllByTranType("C");

        } catch (InvalidDataAccessResourceUsageException e) {
            LOG.error("{}: Ocurrio un error al momento de rescatar las Marcas: ", e);
            return;
        }

        LOG.info("Cantidad de Marcas para generar: {}", Bbookdimens.size());

        if (Bbookdimens.size() > 0) {
            dimensService.EnvioDimens(Bbookdimens, "C");
        }

        try {
            String statusBrand = "T";
            Bbookdimens = (List<BbookEnviaSeason>) bbookenvioseasonrepository.findAllByTranType("D");

        } catch (InvalidDataAccessResourceUsageException e) {
            LOG.error("{}: Ocurrio un error al momento de rescatar las Marcas: ", e);
            return;
        }

        LOG.info("Cantidad de Marcas para generar: {}", Bbookdimens.size());

        if (Bbookdimens.size() > 0) {
            dimensService.EnvioDimens(Bbookdimens, "D");
        }
    }
}

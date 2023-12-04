package sn.ept.git.dic2.vente_velo.Utils;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.primefaces.model.badge.BadgeModel;
import org.primefaces.model.badge.DefaultBadgeModel;

    @Named
    @RequestScoped
    public class MenuBadgeView {

        private BadgeModel badgeModel;

        @PostConstruct
        public void init() {
            badgeModel = DefaultBadgeModel.builder()
                    .severity("danger")
                    .build();
        }

        public BadgeModel getBadgeModel() {
            return badgeModel;
        }
    }


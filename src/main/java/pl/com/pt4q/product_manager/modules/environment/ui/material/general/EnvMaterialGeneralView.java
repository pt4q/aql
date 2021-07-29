package pl.com.pt4q.product_manager.modules.environment.ui.material.general;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.log4j.Log4j2;
import pl.com.pt4q.product_manager.modules.environment.ui.master.general.EnvMasterGeneralView;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvMaterialGeneralView.ROUTE, layout = MainView.class)
@PageTitle(EnvMasterGeneralView.PAGE_TITLE)
public class EnvMaterialGeneralView extends Div {

    public static final String PAGE_TITLE = "Materials";
    public static final String ROUTE = "material";

    public EnvMaterialGeneralView() {

    }
}

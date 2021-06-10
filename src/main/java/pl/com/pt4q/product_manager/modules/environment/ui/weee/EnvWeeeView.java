package pl.com.pt4q.product_manager.modules.environment.ui.weee;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import lombok.extern.log4j.Log4j2;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.modules.environment.ui.master.general.EnvMasterGeneralView;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvWeeeView.ROUTE, layout = MainView.class)
@PageTitle(EnvWeeeView.PAGE_TITLE)
public class EnvWeeeView extends Div implements HasUrlParameter<String> {

    public static final String PAGE_TITLE = "Product WEEE card";
    public static final String ROUTE = EnvMasterDetailView.ROUTE + "-weee";
    public static final String QUERY_PARAM_ID_NAME = "productId";

    public EnvWeeeView() {
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {

    }
}

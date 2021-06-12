package pl.com.pt4q.product_manager.modules.environment.ui.master_general_report;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.log4j.Log4j2;
import pl.com.pt4q.product_manager.modules.environment.ui.master.general.EnvMasterGeneralView;
import pl.com.pt4q.product_manager.view_utils.BackButtonDiv;
import pl.com.pt4q.product_manager.view_utils.DateFromToActionGeneratorFilterDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvMasterReportView.ROUTE, layout = MainView.class)
@PageTitle(EnvMasterReportView.PAGE_TITLE)
public class EnvMasterReportView extends Div {

    public static final String PAGE_TITLE = "Environmental conditions report";
    public static final String ROUTE = EnvMasterGeneralView.ROUTE + "-report";

    private BackButtonDiv backButtonDiv = new BackButtonDiv();
    private DateFromToActionGeneratorFilterDiv reportEditorDiv = new DateFromToActionGeneratorFilterDiv("Date from","Date to","Generate report");

    public EnvMasterReportView() {
        initBackButton();

        VerticalLayout layout = new VerticalLayout(this.backButtonDiv, this.reportEditorDiv);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        setSizeFull();
        add(layout);
    }

    private void initBackButton(){
        this.backButtonDiv.getBackButton().addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(EnvMasterGeneralView.ROUTE);
        });
    }
}

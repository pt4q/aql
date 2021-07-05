package pl.com.pt4q.product_manager.modules.environment.ui.master.general;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import pl.com.pt4q.product_manager.modules.environment.services.master.EnvMasterFinderService;
import pl.com.pt4q.product_manager.modules.environment.ui.master.detail.EnvMasterDetailView;
import pl.com.pt4q.product_manager.modules.environment.ui.master_general_report.EnvMasterReportView;
import pl.com.pt4q.product_manager.view_utils.FilterComboBoxDiv;
import pl.com.pt4q.product_manager.view_utils.FilterTextFieldDiv;
import pl.com.pt4q.product_manager.views.main.MainView;

@Log4j2
@Route(value = EnvMasterGeneralView.ROUTE, layout = MainView.class)
@PageTitle(EnvMasterGeneralView.PAGE_TITLE)
public class EnvMasterGeneralView extends Div {

    public static final String PAGE_TITLE = "Environmental conditions";
    public static final String ROUTE = "environment";

    private FilterTextFieldDiv productFilterDiv = new FilterTextFieldDiv("Filter by product");
    private FilterComboBoxDiv masterStatusFilterDiv = new FilterComboBoxDiv("Filter by product status");
    private EnvMasterGeneralGridDiv masterGridDiv = new EnvMasterGeneralGridDiv();

    private Button addNewProductButton = new Button("Add new env condition");
    private Button createEnvironmentConditionsButton = new Button("Create report");

    private EnvMasterFinderService envMasterFinderService;

    @Autowired
    public EnvMasterGeneralView(EnvMasterFinderService envMasterFinderService) {
        this.envMasterFinderService = envMasterFinderService;

        initAddProductButton();
        initCreateEnvironmentReportButton();

        masterGridDiv.refreshGrid(envMasterFinderService.findAll());

        VerticalLayout layout = new VerticalLayout(createToolPanelDiv(), this.masterGridDiv);
        layout.setWidthFull();

        add(layout);
    }

    private Div createToolPanelDiv() {
        Div leftSideOfToolPanelDiv = new Div();
        HorizontalLayout lsLayout = new HorizontalLayout(this.productFilterDiv, this.masterStatusFilterDiv, this.addNewProductButton);
        lsLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        leftSideOfToolPanelDiv.add(lsLayout);

        Div rightSideOfToolPanelDiv = new Div();
        HorizontalLayout rsLayout = new HorizontalLayout(this.createEnvironmentConditionsButton);
        rsLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        rightSideOfToolPanelDiv.add(rsLayout);

        Div toolPanel = new Div();
        HorizontalLayout tpLayout = new HorizontalLayout(leftSideOfToolPanelDiv, rightSideOfToolPanelDiv);
        tpLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.BASELINE);
        tpLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        toolPanel.add(tpLayout);
        return toolPanel;
    }

    private void initAddProductButton() {
        this.addNewProductButton.addClickListener(buttonClickEvent -> {
            UI ui = UI.getCurrent();
            ui.navigate(EnvMasterDetailView.ROUTE);
        });
    }

    private void initCreateEnvironmentReportButton() {
        this.createEnvironmentConditionsButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(EnvMasterReportView.ROUTE);
        });
    }
}

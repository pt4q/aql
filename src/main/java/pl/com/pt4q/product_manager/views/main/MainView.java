package pl.com.pt4q.product_manager.views.main;

import java.util.Optional;

import pl.com.pt4q.product_manager.modules.environment.ui.master.general.EnvMasterGeneralView;
import pl.com.pt4q.product_manager.modules.product.ui.product.general.ProductsGeneralView;
import pl.com.pt4q.product_manager.modules.product.ui.product_category.ProductCategoriesView;
import pl.com.pt4q.product_manager.modules.product.ui.manufacturer.ManufacturersView;
import pl.com.pt4q.product_manager.modules.product.ui.units.UnitsView;
import pl.com.pt4q.product_manager.modules.test_card.ui.main_view.TestCardsMainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.PageTitle;

/**
 * The main view is a top-level placeholder for other views.
 */
@CssImport("./views/main/main-view.css")
@PWA(name = MainView.PWA_APP_NAME, shortName = MainView.PWA_APP_NAME, enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
public class MainView extends AppLayout {

    public static final String PWA_APP_NAME = "Product manager";
    private final Tabs menu;
    private H1 viewTitle;

    public MainView() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);
        layout.add(new Avatar());
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "AQL logo"));
        logoLayout.add(new H1("AQL"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        return new Tab[]{
//                createTab("Hello World", HelloWorldView.class),
//                createTab("About", AboutView.class),
//                createTab("Person Form", PersonFormView.class),
//                createTab("Master-Detail", MasterDetailView.class),
                createTab(ProductCategoriesView.PAGE_TITLE, ProductCategoriesView.class),
                createTab(ManufacturersView.PAGE_TITLE, ManufacturersView.class),
                createTab(UnitsView.PAGE_TITLE, UnitsView.class),
                createTab(ProductsGeneralView.PAGE_TITLE, ProductsGeneralView.class),
                createTab(EnvMasterGeneralView.PAGE_TITLE, EnvMasterGeneralView.class)
//                createTab(TestCardsMainView.PAGE_TITLE, TestCardsMainView.class)
        };
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(tab -> {
            menu.setSelectedTab(tab);
            resetObjectsInTheContext();
        });
        viewTitle.setText(getCurrentPageTitle());
    }

    //// TODO: 12.05.2021 Remove all objects from the memory after select the other tab
    private void resetObjectsInTheContext() {

    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
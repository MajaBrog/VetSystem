package com.kodilla.veterinary;

import com.kodilla.veterinary.backend.domain.Address;
import com.kodilla.veterinary.backend.domain.Client;
import com.kodilla.veterinary.backend.service.ClientService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;



@Route("")
public class MainView extends VerticalLayout {
//    @Autowired
//    private ClientMapper clientMapper;

    private ClientService clientService;
    private Grid<Client> grid = new Grid<>(Client.class);
    private TextField filterText = new TextField();

    public MainView(ClientService clientService) {
        this.clientService = clientService;
        setSizeFull();
        configureGrid();
        configureFilter();
        add(filterText, grid);

        refresh();
    }

    private void configureFilter() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> refresh());
    }

    private void configureGrid() {
        grid.addClassName("client-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("address");
        grid.setColumns("legalID", "firstName", "lastName", "phoneNumber", "email");
        grid.addColumn(client -> {
            Address address = client.getAddress();
            return address == null ? "-" : address.getStreet() + " " + address.getHouseNumber() + "/" + address.getHomeNumber()
                    + "\n," + address.getPostcode() + " " + address.getCity();
        }).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void refresh() {
        grid.setItems(clientService.getAllClients());
    }
/*    private void refresh() {
        grid.setItems(clientMapper.mapToClientDtoList(clientService.getAllClients()));
    }*/
}

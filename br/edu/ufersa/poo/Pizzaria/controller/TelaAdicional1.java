package br.edu.ufersa.poo.Pizzaria.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.ufersa.poo.Pizzaria.dao.AdicionalDao;
import br.edu.ufersa.poo.Pizzaria.model.entity.Adicional;
import br.edu.ufersa.poo.Pizzaria.view.Telas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class TelaAdicional1 implements Initializable {
  public static Telas telas = new Telas();
  @FXML private Label nome1, nome2, nome3, nome4, nome5, nome6, nome7, nome8, nome9, nome10, nome11;
  @FXML private Label preco1, preco2, preco3, preco4, preco5, preco6, preco7, preco8, preco9, preco10, preco11;
  @FXML private Label quantidade1, quantidade2, quantidade3, quantidade4, quantidade5, quantidade6, quantidade7, quantidade8, quantidade9, quantidade10, quantidade11;
  @FXML private ImageView quad1, quad2, quad3, quad4, quad5, quad6, quad7, quad8, quad9, quad10, quad11;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    AdicionalDao adicionalDao = new AdicionalDao();

    List<Adicional> adicionais = adicionalDao.listar();

    List<Label> nomes = List.of(nome1, nome2, nome3, nome4, nome5, nome6, nome7, nome8, nome9, nome10, nome11);
    List<Label> precos = List.of(preco1, preco2, preco3, preco4, preco5, preco6, preco7, preco8, preco9, preco10, preco11);
    List<Label> quantidades = List.of(quantidade1, quantidade2, quantidade3, quantidade4, quantidade5, quantidade6, quantidade7, quantidade8, quantidade9, quantidade10, quantidade11);
    List<ImageView> imgs = List.of(quad1, quad2, quad3, quad4, quad5, quad6, quad7, quad8, quad9, quad10, quad11);
  
    for (int i = 0; i < adicionais.size(); i++) {
      nomes.get(i).setText(adicionais.get(i).getNome());
      nomes.get(i).setVisible(true);
      precos.get(i).setText("$ " + adicionais.get(i).getValor());
      precos.get(i).setVisible(true);
      quantidades.get(i).setText(String.valueOf(adicionais.get(i).getQuantidade()));
      quantidades.get(i).setVisible(true);
      imgs.get(i).setVisible(true);
    }
  }
}

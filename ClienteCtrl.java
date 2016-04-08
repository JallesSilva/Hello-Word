package br.com.LojaVirtual.Negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.LojaVirtual.Persistencia.ClienteDAO;
import br.com.LojaVirtual.beans.Cliente;
import br.com.LojaVirtual.beans.Fone;

@SessionScoped
@ManagedBean
public class ClienteCtrl implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListagem() {
		return ClienteDAO.listagem();
	}

	public String actionGravar() {
		if (cliente.getId() == 0) {
			ClienteDAO.inserir(cliente);
			return actionInserir();
		} else {
			ClienteDAO.alterar(cliente);
			return "lista_cliente";
		}
	}

	public String actionInserir() {
		cliente = new Cliente();
		return "form_cliente";
	}

	public String actionExcluir(Cliente p) {
		ClienteDAO.excluir(p);
		return "lista_cliente";
	}

	public String actionAlterar(Cliente p) {
		cliente = p;
		return "form_cliente";
	}

	public String actionInserirFone() {
		Fone fone = new Fone();
		fone.setCliente(cliente);
		cliente.getFones().add(fone);
		return "form_cliente";
	}

	public void actionExcluirFone(Fone f) {
		ClienteDAO.excluirFone(f);
		cliente.getFones().remove(f);
	}

}

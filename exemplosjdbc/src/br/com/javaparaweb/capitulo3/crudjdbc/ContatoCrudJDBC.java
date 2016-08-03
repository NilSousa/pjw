package br.com.javaparaweb.capitulo3.crudjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContatoCrudJDBC {

	public void salvar(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement insereSt = null;
		String sql = "insert into contato (nome, telefone, email, dt_cad, obs) values (?,?,?,?,?)";
		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setString(1, contato.getNome());
			insereSt.setString(2, contato.getTelefone());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, contato.getDataCadastro());
			insereSt.setString(5, contato.getObservacao());
			insereSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao incluir contato. Mensagem: " + e.getMessage());
		} finally {
			try {
				insereSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de inserção. Mensagem: " + e.getMessage());
			}
		}

	}

	public void atualizar(Contato contato) {

	}

	public void excluir(Contato contato) {

	}

	public List<Contato> listar() {
		Connection conexao = this.geraConexao();
		List<Contato> contatos = new ArrayList<Contato>();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		String sql = "select * from contato";
		try {
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			while(resultado.next()){
				contato = new Contato();
				contato.setCodigo(resultado.getInt("codigo"));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("dt_cad"));
				contato.setObservacao("obs");
				contatos.add(contato);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar código do contato. Mensagem: " + e.getMessage());
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: " + e.getMessage());
			}
		}
		return contatos;
	}

	public Contato buscaContato(int valor) {
		return null;
	}

	public Connection geraConexao() {
		return null;
	}

	public static void main(String args[]) {

	}
}

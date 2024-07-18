package Dominio;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ServicioPedidos {
	private JsonNode rootDias;
	private JsonNode rootMenus;
	private final ObjectMapper mapper;
	private final Scanner scanner;
	private String detallesAlmuerzoComprado;
	private int numeroRetiro;

	private Pagos pagos;

	public ServicioPedidos() {
		mapper = new ObjectMapper();
		scanner = new Scanner(System.in);
		cargarJson();
	}

	private void cargarJson() {
		try {
			rootDias = cargarArchivoJson("src/main/java/Datos/dia.json");
			rootMenus = cargarArchivoJson("src/main/java/Datos/menu.json");
		} catch (IOException e) {
			System.out.println("Error al cargar los archivos JSON: " + e.getMessage());
		}
	}

	private JsonNode cargarArchivoJson(String rutaArchivo) throws IOException {
		File archivo = new File(rutaArchivo);
		if (!archivo.exists()) {
			throw new IOException("Archivo JSON no encontrado en la ruta especificada: " + archivo.getAbsolutePath());
		}
		return mapper.readTree(archivo);
	}

	public Menu obtenerMenu(String dia, String tipoMenu) {
		JsonNode menusNode = cargarMenusDelDia(dia);
		if (menusNode == null) return null;
		return Menu.fromJsonNode(menusNode.get(tipoMenu));
	}

	private JsonNode cargarMenusDelDia(String dia) {
		try {
			return rootMenus.get("dias").get(dia).get("menus");
		} catch (Exception e) {
			System.out.println("Error al cargar los menús para el día: " + e.getMessage());
			return null;
		}
	}


	public String seleccionarMenuDetalles(String bebestible, String fondo, String ensalada, String postre, String sopa, String pan) {
		String almuerzoComprado = "";
		almuerzoComprado += bebestible+ ", ";
		almuerzoComprado += fondo+ ", ";
		almuerzoComprado += ensalada+ ", ";
		almuerzoComprado += postre+ ", ";
		almuerzoComprado += sopa+ ", ";
		almuerzoComprado += pan;

		return almuerzoComprado.endsWith(", ") ? almuerzoComprado.substring(0, almuerzoComprado.length() - 2) : almuerzoComprado;
	}


	public int actualizarJsonDia(String dia, Cliente cliente, String almuerzoComprado) throws IOException {

		File jsonFileDias = new File("src/main/java/Datos/dia.json");
		JsonNode rootDias = mapper.readTree(jsonFileDias);
		JsonNode diaNode = rootDias.get("dia").get(dia);
		if (diaNode == null) {
			System.out.println("El nodo para el día " + dia + " no existe.");
			return -1;
		}
		ObjectNode almuerzosCompradosNode = obtenerNodoAlmuerzosCompradosDia(diaNode);
		return agregarAlmuerzoDia(almuerzosCompradosNode, cliente, almuerzoComprado, jsonFileDias, rootDias);
	}

	private ObjectNode obtenerNodoAlmuerzosCompradosDia(JsonNode diaNode) {
		ObjectNode almuerzosCompradosNode = (ObjectNode) diaNode.get("almuerzosComprados");
		if (almuerzosCompradosNode == null) {
			almuerzosCompradosNode = mapper.createObjectNode();
			((ObjectNode) diaNode).set("almuerzosComprados", almuerzosCompradosNode);
		}
		return almuerzosCompradosNode;
	}

	private int agregarAlmuerzoDia(ObjectNode almuerzosCompradosNode, Cliente cliente, String almuerzoComprado, File jsonFileDias, JsonNode rootDias) throws IOException {
		int numAlmuerzos = obtenerNumeroAlmuerzos(almuerzosCompradosNode);
		ObjectNode nuevoAlmuerzo = mapper.createObjectNode();
		ArrayNode detallesArray = nuevoAlmuerzo.putArray("detalles");
		for (String detalle : almuerzoComprado.split(", ")) {
			detallesArray.add(detalle);
		}
		nuevoAlmuerzo.put("correoElectronico", cliente.getCorreoElectronico());
		int numeroAsignado = numAlmuerzos + 1;
		almuerzosCompradosNode.set(String.valueOf(numeroAsignado), nuevoAlmuerzo);
		guardarJson(jsonFileDias, rootDias);
		return numeroAsignado;
	}

	private int obtenerNumeroAlmuerzos(ObjectNode almuerzosCompradosNode) {
		int numAlmuerzos = 0;
		Iterator<Map.Entry<String, JsonNode>> fields = almuerzosCompradosNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			int currentNum = Integer.parseInt(field.getKey());
			if (currentNum > numAlmuerzos) {
				numAlmuerzos = currentNum;
			}
		}
		return numAlmuerzos;
	}

	private void guardarJson(File archivo, JsonNode root) throws IOException {
		mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, root);
	}

	public String verAlmuerzosComprados(Cliente cliente) {
		StringBuilder historial = new StringBuilder();
		try {
			rootDias = cargarArchivoJson("src/main/java/Datos/dia.json");

			JsonNode diasNode = rootDias.get("dia");
			for (Iterator<String> it = diasNode.fieldNames(); it.hasNext(); ) {
				String dia = it.next();
				JsonNode almuerzosNode = diasNode.get(dia).get("almuerzosComprados");
				if (almuerzosNode != null) {
					for (Iterator<Map.Entry<String, JsonNode>> itAlmuerzos = almuerzosNode.fields(); itAlmuerzos.hasNext(); ) {
						Map.Entry<String, JsonNode> almuerzoEntry = itAlmuerzos.next();
						JsonNode almuerzoNode = almuerzoEntry.getValue();
						if (almuerzoNode.get("correoElectronico").asText().equals(cliente.getCorreoElectronico())) {
							historial.append("Día: ").append(dia).append("\n");
							historial.append("Código de retiro: ").append(almuerzoEntry.getKey()).append("\n");
							historial.append("Detalles: ").append(almuerzoNode.get("detalles")).append("\n");
							historial.append("-----------\n");
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error al leer los almuerzos comprados: " + e.getMessage());
		}
		return historial.toString();
	}


	public int getNumeroRetiro() {
		return numeroRetiro;
	}

}


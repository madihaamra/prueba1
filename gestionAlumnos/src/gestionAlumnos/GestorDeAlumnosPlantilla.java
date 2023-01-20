
package gestionAlumnos;

import java.util.Scanner;

import gestionAlumnos.Alumno;

/**
 * Esta clase permite: Añadir un Alumno y guardarlos en el Array; Visualizar
 * todos los Alumnos; Visualizar un Alumno por nombre.
 */
public class GestorDeAlumnosPlantilla {

	private Scanner teclado = null;
	private Alumno[] alumnos = null; // Array de alumnos

	public GestorDeAlumnosPlantilla() {
		teclado = new Scanner(System.in);
		alumnos = new Alumno[300];
	}

	// ----------------- ARRAYS ---------------------

	// Guarda un Alumno en el Array
	public void anadirAlumno(Alumno alumno) {
		for (int i = 0; i < alumnos.length; i++) {
			if (null == alumnos[i]) {
				alumnos[i] = alumno;
				break;
			}
		}
	}

	// Devuelve TODOS los Alumnos
	public Alumno[] getTodosAlumnos() {
		return alumnos;
	}

	// Devuelve un Alumno por nombre
	public Alumno getAlumno(String nombre) {
		Alumno ret = null;
		for (int i = 0; i < alumnos.length; i++) {
			Alumno alumnoTemp = alumnos[i];
			if ((null != alumnoTemp) && (alumnoTemp.getNombre().equals(nombre))) {
				ret = alumnos[i];
				break;
			}
		}
		return ret;
	}

	// ----------------- MENUS ---------------------

	// Funcion principal
	public void iniciar() {
		int opcion = 0;
		do {
			opcion = opcionMenu();
			if (opcion != 0) {
				ejecutarOpcionMenu(opcion);
				System.out.println(" ");
			}
		} while (opcion != 0);
		System.out.print("Adios!!!");
	}

	// Opciones del menu
	private int opcionMenu() {
		int ret = 0;
		do {
			try {
				escribirMenu();
				System.out.print("Elija una opcion: ");
				ret = teclado.nextInt();
				teclado.nextLine();
			} catch (Exception e) {
				teclado.nextLine();
				ret = -1;
			}
		} while ((ret < 0) && (ret > 8));
		return ret;
	}

	// Escribe los literales del menu
	private void escribirMenu() {
		System.out.println("---- MENU ----");
		System.out.println("---- 0 - SALIR ");
		System.out.println("---- 1 - Aniadir  un  Alumno ");
		System.out.println("---- 2 - Visualizar todos los Alumnos ");
		System.out.println("---- 3 - Visualizar un Alumno ");
		System.out.println("---- 4 - Modificar un Alumno ");
		System.out.println("---- 5 - Borrar un Alumno ");
		System.out.println("---- 6 - Insertar Nota ");
		System.out.println("---- 7 - Modificar Nota ");
		System.out.println("---- 8 - Borrar Nota ");

		System.out.println("--------------");
	}

	// ----------------- OPCIONES ---------------------

	// Ejecuta la opcion de menu elegida
	private void ejecutarOpcionMenu(int opcion) {
		switch (opcion) {
		case 1:
			ejecutarOpcionMenuAñadirAlumno();
			break;
		case 2:
			ejecutarOpcionMostrarTodosLosAlumnos();
			break;
		case 3:
			ejecutarOpcionMostrarAlumnoPorNombre();
			break;
		case 4:
			modificarAlumno();
			break;
		case 5:
			borrarAlumno();
			break;
		case 6:
			insertarNota();
			break;
		case 7:
			modificarNota();
			break;
		case 8:
			borrarNota();
			break;
		}
	}

	private void borrarNota() {
		System.out.print("Nombre - ");
		String nombre = leerOperandoText();
		Alumno alumno = getAlumno(nombre);

		if (null != alumno) {
			System.out.println("Nota del alumno - :" + alumno.getNota());

			for (int i = 0; i < alumnos.length; i++) {
				Alumno alumnoTemp = alumnos[i];
				if ((null != alumnoTemp) && (alumnoTemp.getNombre().equals(nombre))) {

					alumnoTemp.setNota(0);
					System.out.println("hecho!!");

					break;
				}
			}

		} else {
			System.out.println("El alumno " + nombre + " no existe");
		}

	}

	private void modificarNota() {
		System.out.print("Nombre - ");
		String nombre = leerOperandoText();
		Alumno alumno = getAlumno(nombre);

		if (null != alumno) {
			System.out.println("Nota - :" + alumno.getNota());
			System.out.println("la Nota nueva del alumno: - ");
			int nota = leerOperandoInt();

			for (int i = 0; i < alumnos.length; i++) {
				Alumno alumnoTemp = alumnos[i];
				if ((null != alumnoTemp) && (alumnoTemp.getNombre().equals(nombre))) {

					alumnoTemp.setNota(nota);

					break;
				}
			}

		} else {
			System.out.println("El alumno " + nombre + " no existe");
		}

	}

	private void insertarNota() {

		System.out.print("Nombre - ");
		String nombre = leerOperandoText();
		Alumno alumno = getAlumno(nombre);

		if (null != alumno) {
			System.out.print("Nota - ");
			int nota = leerOperandoInt();

			for (int i = 0; i < alumnos.length; i++) {
				Alumno alumnoTemp = alumnos[i];
				if ((null != alumnoTemp) && (alumnoTemp.getNombre().equals(nombre))) {

					alumnoTemp.setNota(nota);

					break;
				}
			}

		} else {
			System.out.println("El alumno " + nombre + " no existe");
		}

	}

	private void modificarAlumno() {
		System.out.print("Nombre - ");
		String nombre = leerOperandoText();
		Alumno alumno = getAlumno(nombre);

		if (null != alumno) {
			Alumno alumnoModificado = introducirNuevoAlumno();
			anadirAlumnoModificado(alumnoModificado, nombre);
		} else {
			System.out.println("El alumno " + nombre + " no existe");
		}

	}

	public void anadirAlumnoModificado(Alumno alumnoModificado, String nombre) {
		for (int i = 0; i < alumnos.length; i++) {
			Alumno alumnoTemp = alumnos[i];
			if ((null != alumnoTemp) && (alumnoTemp.getNombre().equals(nombre))) {
				alumnos[i] = alumnoModificado;
				break;
			}
		}
	}

	private void borrarAlumno() {
		System.out.print("Nombre - ");
		String nombre = leerOperandoText();
		Alumno alumno = getAlumno(nombre);

		if (null != alumno) {

			for (int i = 0; i < alumnos.length; i++) {
				Alumno alumnoTemp = alumnos[i];
				if ((null != alumnoTemp) && (alumnoTemp.getNombre().equals(nombre))) {
					alumnos[i] = null;
					break;
				}
			}
		} else {
			System.out.println("El alumno " + nombre + " no existe");
		}

	}

	// Opcion aniadir alumno
	private void ejecutarOpcionMenuAñadirAlumno() {
		Alumno alumno = introducirNuevoAlumno();
		anadirAlumno(alumno);
	}

	// Opcion mostrar todos los alumnos
	private void ejecutarOpcionMostrarTodosLosAlumnos() {
		mostrarTodosLosAlumnos();
	}

	// Opcion mostrar alumno por nombre
	private void ejecutarOpcionMostrarAlumnoPorNombre() {
		System.out.print("Nombre - ");
		String nombre = leerOperandoText();
		Alumno alumno = getAlumno(nombre);
		if (null != alumno) {
			mostrarAlumno(alumno);
		} else {
			System.out.println("El alumno " + nombre + " no existe");
		}
	}

	// ----------------- TECLADO ---------------------

	// Lee del teclado un numero
	private int leerOperandoInt() {
		int ret = 0;
		do {
			try {
				System.out.print(" : ");
				ret = teclado.nextInt();
				teclado.nextLine();
			} catch (Exception e) {
				teclado.nextLine();
				ret = -1;
			}
		} while (ret < 0);
		return ret;
	}

	// Lee del teclado un texto
	private String leerOperandoText() {
		String ret = null;
		try {
			System.out.print(" : ");
			ret = teclado.nextLine();
		} catch (Exception e) {
			teclado.nextLine();
		}
		return ret;
	}

	// ----------------- EJECUCION ---------------------

	// Pide al usuario los datos de un nuevo Alumno
	private Alumno introducirNuevoAlumno() {
		Alumno ret = new Alumno();
		System.out.println("-- Datos del Alumno --");
		System.out.print("Nombre - ");
		ret.setNombre(leerOperandoText());
		System.out.print("Apellido 1 - ");
		ret.setApellido1(leerOperandoText());
		System.out.print("Apellido 2 - ");
		ret.setApellido2(leerOperandoText());
		System.out.print("Fecha De Nacimiento - ");
		ret.setFechaDeNacimiento(leerOperandoText());
		System.out.print("Curso - ");
		ret.setCurso(leerOperandoInt());
		System.out.print("Clase - ");
		ret.setClase(leerOperandoInt());
		System.out.println("----------");
		return ret;
	}

	// Muestra todos los alumnos del array
	private void mostrarTodosLosAlumnos() {
		for (int i = 0; i < alumnos.length; i++) {
			if (null != alumnos[i]) {
				mostrarAlumno(alumnos[i]);
			}
		}
	}

	// Muestra un solo alumno
	private void mostrarAlumno(Alumno alumno) {
		System.out.println("----------");
		if (null != alumno) {
			System.out.println("Nombre: " + alumno.getNombre());
			System.out.println("Apellido 1: " + alumno.getApellido1());
			System.out.println("Apellido 2: " + alumno.getApellido2());
			System.out.println("Fecha De Nacimiento: " + alumno.getFechaDeNacimiento());
			System.out.println("Curso: " + alumno.getCurso());
			System.out.println("Clase: " + alumno.getClase());
			System.out.println("Nota: " + alumno.getNota());
		} else {
			System.out.println("Sin datos");
		}
		System.out.println("----------");
	}

	// ----------------- MAIN ---------------------

	public static void main(String[] args) {
		GestorDeAlumnosPlantilla gestorDeAlumnos = new GestorDeAlumnosPlantilla();
		gestorDeAlumnos.iniciar();
	}
}
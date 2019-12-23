package com.monitor.service;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.monitor.filter.FiltrosMonitor;
import com.monitor.model.Foto;
import com.monitor.persistencia.Persistencia;

public class LoadImgService {
	private ArrayList<Foto> listFotoDescarga;
	private static final Logger LOGGER = LoggerFactory.getLogger(LoadImgService.class);
	@ManagedProperty("#{persistencia}")
	public Persistencia persistencia;
	private StreamedContent fotoMainPage;
	static File defaultFile = null;

	static {
		ImageIO.scanForPlugins();
	}

	public ByteArrayOutputStream dowload(ArrayList<Foto> listFotoDescarga) throws IOException {
		boolean error = false;
		HashMap<String, Foto> mapFotos = new HashMap<>();
		this.listFotoDescarga = listFotoDescarga;
		BufferedImage img = null;
		// se quitan fotos duplicadas
		for (int i = 0; i < listFotoDescarga.size(); i++) {
			mapFotos.put(listFotoDescarga.get(i).getFotoPath(), listFotoDescarga.get(i));
		}

		Iterator<Map.Entry<String, Foto>> it = mapFotos.entrySet().iterator();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ZipOutputStream out = new ZipOutputStream(os);
		while (it.hasNext()) {
			Map.Entry<String, Foto> pair = it.next();
			Foto foto = (Foto) pair.getValue();
			File imgFileDisk = new File(foto.getFotoPath());
			FileInputStream fileInputStream=null;
			try {
				fileInputStream = new FileInputStream(imgFileDisk);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				error = true;
				e.printStackTrace();
			}

			if (error) {
				try {
					defaultFile = new File(defaultImg());
					fileInputStream = new FileInputStream(defaultFile);					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LOGGER.error("Error : ", e);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LOGGER.error("Error : ", e);
				}
			}

			try {
				img = ImageIO.read(fileInputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pathImgStr="";
			try {
				pathImgStr = FilenameUtils.getPath(imgFileDisk.getAbsolutePath())
						+ FilenameUtils.getBaseName(imgFileDisk.getCanonicalPath()) + "."
						+ FilenameUtils.getExtension(imgFileDisk.getPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.putNextEntry(new ZipEntry(pathImgStr));
			ImageIO.write(img, "jpg", out);
			out.closeEntry();

		}
		out.close();
		return os;

	}

	public ByteArrayOutputStream dowloadPorUsuario(String cveCliPro) throws Exception {
		FotoService fotoService = new FotoService(persistencia.getEntityManager());
		FiltrosMonitor filtrosMonitor = new FiltrosMonitor();
		filtrosMonitor.setCveClipro(cveCliPro);
		listFotoDescarga = fotoService.obtenerFotosPorUsuario(filtrosMonitor);
		return dowload(listFotoDescarga);

	}

	public Persistencia getPersistencia() {
		return persistencia;
	}

	public void setPersistencia(Persistencia persistencia) {
		this.persistencia = persistencia;
	}

	public StreamedContent getFotoMainPage(String path, FacesContext context) {
		boolean error = false;

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();

		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			System.out.println("path" + path);
			if (path != null && !path.isEmpty()) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(new FileInputStream(path));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					error = true;
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

				if (error) {
					try {
						defaultFile = new File(defaultImg());
						img = ImageIO.read(new FileInputStream(defaultFile));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						LOGGER.error("Error : ", e);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						LOGGER.error("Error : ", e);
					}
				}
				// image is scaled two times at run time

				int w = 300;
				int h = 200;
				BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.getGraphics();
				g.drawImage(img, 0, 0, w, h, null);
				try {
					ImageIO.write(bi, "png", bos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				fotoMainPage = new DefaultStreamedContent(new ByteArrayInputStream(bos.toByteArray()), "image/png");
				return fotoMainPage;
			}

		}

		return fotoMainPage;
	}

	public StreamedContent getFotoMainPage(String path) {
		FacesContext context = FacesContext.getCurrentInstance();
		fotoMainPage = getFotoMainPage(path, context);
		return fotoMainPage;
	}

	public String defaultImg() {
		String path = "";
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String currentPath = ctx.getRealPath(File.separator);
		if (currentPath.endsWith(File.separator)) {
			path = ctx.getRealPath(File.separator) + "resources" + File.separator + "img" + File.separator
					+ "noDisponible.jpg";
		} else {
			path = ctx.getRealPath(File.separator) + File.separator + "resources" + File.separator + "img"
					+ File.separator + "noDisponible.jpg";
			System.out.println("path " + path);
		}

		return path;

	}

}

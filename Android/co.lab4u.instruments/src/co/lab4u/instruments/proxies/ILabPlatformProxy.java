package co.lab4u.instruments.proxies;

import org.json.JSONException;

import co.lab4u.instruments.models.ILaboratory;

public interface ILabPlatformProxy {
	ILaboratory getLaboratory(int idLab);
}

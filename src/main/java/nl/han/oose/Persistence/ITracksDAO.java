package nl.han.oose.Persistence;

import nl.han.oose.entity.TrackDB;

import java.util.List;

public interface ITracksDAO {
    List<TrackDB> getAllTracks();
}

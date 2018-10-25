package nl.han.oose.Persistence;

import nl.han.oose.entity.TracksDB;

import java.util.List;

public interface ITracksDAO {
    List<TracksDB> getAllTracks();
}

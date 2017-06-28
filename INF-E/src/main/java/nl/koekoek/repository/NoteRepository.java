package nl.koekoek.repository;

import java.util.List;

import nl.koekoek.model.Note;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Finds all notes for a server.
     * @param serverId of the server
     * @return list of notes
     */
    List<Note> findByServerId(long serverId);
}

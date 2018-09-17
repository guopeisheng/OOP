package model.visitor;

import provided.music.*;
import provided.player.*;
import provided.util.*;

public class PlayVisitor extends APhraseVisitor {

	private KeySignature keySig = null;

	/**
	 * PlayVisitor constructor
	 */
	public PlayVisitor() {
		String header = "ABCDEFGHIJMNOPRSTUVWXYZ";

		this.addCmd(Note.ID, new IPhraseVisitorCmd() {
			public Object apply(String id, IPhrase host, Object... params) {
				SequencePlayer musicPlayer = (SequencePlayer) params[0];
				//previous note
				Note prevNote = (Note) host;
				//adjust to new note
				Note newNote = keySig.adjust(prevNote);
				return musicPlayer.addNote(newNote, (int) params[1]);
			}
		});

		this.addCmd(Chord.ID, new IPhraseVisitorCmd() {
			public Object apply(String id, IPhrase host, Object... params) {
				//cast down from host to chord
				Chord chord = (Chord) host;
				Note[] notes = (chord).getNotes();

				//take out 1st tick
				int Tick1 = (int) params[1];

				//execute each element in notes:
				for (Note note : notes) {
					Tick1 = (int) note.execute(PlayVisitor.this, params);
				}
				return Tick1;
			}
		});

		this.addCmd(Triplet.ID, new IPhraseVisitorCmd() {
			public Object apply(String id, IPhrase host, Object... params) {
				//cast down from host to Triplet
				Triplet triplete = (Triplet) host;
				Note[] notes = (triplete).getNotes();

				//take out 1st tick
				int Tick1 = (int) params[1];

				//execute each element in notes:
				for (Note note : notes) {
					note.setDuration(note.getDuration() * 2 / 3); //scale duration
					Tick1 = (int) note.execute(PlayVisitor.this, params);
				}
				return Tick1;
			}
		});

		this.addCmd(MTSeqList.ID, new IPhraseVisitorCmd() {
			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				return params[1];
			}
		});

		this.addCmd(NESeqList.ID, new IPhraseVisitorCmd() {
			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				NESeqList NEList = (NESeqList) host;
				//execute first element in list
				//return processed list
				int Tick1 = (int) NEList.getFirst().execute(PlayVisitor.this, params);
				//execute the rest elements in list
				//return processed list
				Tick1 = (int) NEList.getRest().execute(PlayVisitor.this, params[0], Tick1);
				return Tick1;
			}

		});

		//for loop for those cases that header is special character 
		for (int i = 0; i < header.length(); i++) {
			this.addCmd("" + header.charAt(i), new IPhraseVisitorCmd() {
				@Override
				public Object apply(String id, IPhrase host, Object... params) {
					return params[1];
				}
			});
		}

		// default length of a note in the file
		this.addCmd("L", new IPhraseVisitorCmd() {
			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				Header header = (Header) host;
				double parseFract = ABCUtil.Singleton.parseFraction(header.getValue());
				SequencePlayer player = (SequencePlayer) params[0];
				//4 tick per quarter note
				parseFract *= player.getTicksPerQuarterNote() * 4;
				player.setTicksPerDefaultNote((int) parseFract);
				return params[1];
			}
		});

		//this header specifies the tempo for the piece
		this.addCmd("Q", new IPhraseVisitorCmd() {
			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				Header header = (Header) host;
				SequencePlayer player = (SequencePlayer) params[0];
				//calculate the default number of notes per quarter note
				double defaultNotesPerQuarterNote = player.getTicksPerQuarterNote() / player.getTicksPerDefaultNote();
				int bpm = (int) ABCUtil.Singleton.parseTempo(header.getValue(), defaultNotesPerQuarterNote);
				player.setTempo(bpm);
				return params[1];
			}
		});

		//key signature for the music
		this.addCmd("K", new IPhraseVisitorCmd() {
			@Override
			public Object apply(String id, IPhrase host, Object... params) {
				Header header = (Header) host;
				keySig = new KeySignature(header.getValue());
				return params[1];
			}
		});
	}
}

package Music_App;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

class Song {
    public String title;
    public String artist;
    public float duration; // duration in seconds

    public Song(String title, String artist, float duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void play() {
        System.out.println("Playing: " + title + " by " + artist + " [" + duration + " seconds]");
    }

    @Override
    public String toString() {
        return title + " by " + artist + " (" + duration + " sec)";
    }
}

class Playlist {
    public String name;
    public LinkedList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new LinkedList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
        System.out.println(song.getTitle() + " Added to Playlist.");
    }

    public void removeSong(String title) {
        boolean found = false;
        for (Song song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                songs.remove(song);
                System.out.println(title + " Removed From Playlist.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Song not found in Playlist.");
        }
    }

    public void rearrangeSong(int fromIndex, int toIndex) {
        if (fromIndex < songs.size() && toIndex < songs.size() && fromIndex != toIndex) {
                Song song = songs.remove(fromIndex);
            songs.add(toIndex, song);
            System.out.println("Song moved to new position.");
        } else {
            System.out.println("Invalid indices for rearranging.");
        }
    }


    public void displayPlaylist() {
        System.out.println("Playlist: " + name);
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
    }
}

public class MusicPlaylistApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================================================================");
        System.out.println("                   Welcome To Tingle Music App");
        System.out.println("=================================================================================");
        System.out.print("\nCreate Your Playlist (Playlist_Name): ");
        String playlistName = scanner.nextLine();
        Playlist playlist = new Playlist(playlistName);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Song");
            System.out.println("3. Rearrange Song");
            System.out.println("4. Display Playlist");
            System.out.println("5. Play Songs");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter duration in seconds: ");
                    float duration = scanner.nextFloat();
                    scanner.nextLine();  // consume newline
                    Song newSong = new Song(title, artist, duration);
                    playlist.addSong(newSong);
                    break;

                case 2:
                    System.out.print("Enter the title of the song to remove: ");
                    String removeTitle = scanner.nextLine();
                    playlist.removeSong(removeTitle);
                    break;

                case 3:
                    System.out.print("Enter the Position of the song to move from: ");
                    int fromIndex = scanner.nextInt() -1 ;
                    System.out.print("Enter the Position to move to: ");
                    int toIndex = scanner.nextInt() -1 ;
                    playlist.rearrangeSong(fromIndex, toIndex);
                    break;


                case 4:
                    playlist.displayPlaylist();
                    break;

                case 5:
                    if (playlist.songs.isEmpty()) {
                        System.out.println("The playlist is empty. Add songs to play.");
                        break;
                    }

                    int currentIndex = 0;
                    boolean isPlaying = true;
                    boolean repeatAll = false;

                    while (isPlaying) {
                        System.out.println("\nNow Playing: " + playlist.songs.get(currentIndex));
                        playlist.songs.get(currentIndex).play();

                        System.out.println("\nPlayback Options:");
                        System.out.println("1. Next");
                        System.out.println("2. Previous");
                        System.out.println("3. Repeat Current Song");
                        System.out.println("4. Repeat All Songs");
                        System.out.println("5. Shuffle Songs");
                        System.out.println("6. Stop");
                        System.out.println("7. Back to Main Menu");
                        System.out.print("Choose an option: ");

                        int playbackOption = scanner.nextInt();
                        scanner.nextLine(); // consume newline

                        switch (playbackOption) {
                            case 1: // Next Song
                                if (currentIndex < playlist.songs.size() - 1) {
                                    currentIndex++;
                                } else {
                                    System.out.println("Reached the end of the playlist.");
                                }
                                break;

                            case 2: // Previous Song
                                if (currentIndex > 0) {
                                    currentIndex--;
                                } else {
                                    System.out.println("You are at the beginning of the playlist.");
                                }
                                break;

                            case 3: // Repeat Current Song
                                System.out.println("Repeating current song...");
                                break;

                            case 4: // Repeat All Songs
                                repeatAll = true;
                                System.out.println("Repeat All Songs enabled.");
                                break;

                            case 5: // Shuffle Songs
                                System.out.println("Shuffling playlist...");
                                Collections.shuffle(playlist.songs, new Random());
                                currentIndex = 0; // Reset index to start shuffled playback
                                playlist.displayPlaylist();
                                break;

                            case 6: // Stop
                                System.out.println("Stopping playback.");
                                isPlaying = false;
                                break;

                            case 7: // Back to Main Menu
                                System.out.println("Returning to main menu...");
                                isPlaying = false;
                                break;

                            default:
                                System.out.println("Invalid option. Please try again.");
                        }

                        // Handle Repeat All Songs logic
                        if (repeatAll && !isPlaying) {
                            isPlaying = true;
                            currentIndex = 0; // Reset to start playback again
                        }
                    }
                    break;


                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}

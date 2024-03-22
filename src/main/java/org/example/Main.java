package org.example;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        makeTestData();
        while (true) {
            System.out.print("명령어 : ");
            String cmd = scan.nextLine();
            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            switch (cmd) {
                case "add" -> add();
                case "list" -> list();
                case "update" -> update();
                case "delete" -> delete();
                case "detail" -> detail();
                case "search" -> search();
                default -> System.out.println("올바른 명령어가 아닙니다.");
            }
        }
    }

    static ArrayList<Article> articleList = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    static int latestArticleId = 4;

    private static void search() {
        System.out.print("검색 키워드를 입력해주세요 :");
        String keyword = scan.nextLine();
        ArrayList<Article> searchedList = new ArrayList<>();

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getTitle().contains(keyword)) {
                searchedList.add(article);
            }
        }
        printArticleList(searchedList);
    }

    private static void detail() {
        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());
        int index = findIndexById(inputId);

        if (index == -1) {
            System.out.println("존재하지 않는 게시물 번호입니다.");
            return;
        }
        Article article = articleList.get(index);
        article.increaseHit();

        System.out.println("==================");
        System.out.println("번호 : " + article.getId());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getBody());
        System.out.println("등록날짜 : " + article.getRegDate());
        System.out.println("조회수 : " + article.getHit());
        System.out.println("==================");
    }

    private static void delete() {
        System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());
        int index = findIndexById(inputId);

        if (index == -1) {
            System.out.println("존재하지 않는 게시물 번호입니다.");
            return;
        }
        articleList.remove(index);
        System.out.printf("%d 게시물이 삭제되었습니다.\n", inputId);
    }

    private static void update() {
        System.out.print("업데이트할 게시물 번호를 입력해주세요 : ");
        int inputId = Integer.parseInt(scan.nextLine());
        int index = findIndexById(inputId);
        if (index == -1) {
            System.out.println("존재하지 않는 게시물 번호입니다.");
            return;
        }
        System.out.print("새로운 제목을 입력해주세요 : ");
        String newTitle = scan.nextLine();
        System.out.print("새로운 내용을 입력해주세요 : ");
        String newBody = scan.nextLine();

        Article target = articleList.get(index);
        target.setTitle(newTitle);
        target.setBody(newBody);
        System.out.printf("%d번 게시물이 수정되었습니다.\n", inputId);
    }


    private static void list() {
        printArticleList(articleList);
    }

    private static void add() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String body = scan.nextLine();
        Article article = new Article(latestArticleId, title, body, 0, getCurrentDateTime());
        articleList.add(article);
        System.out.println("게시물이 등록되었습니다.");
        latestArticleId++;
    }

    public static void printArticleList(ArrayList<Article> targetList) {
        System.out.println("==================");
        for (int i = 0; i < targetList.size(); i++) {
            Article article = targetList.get(i);
            System.out.println("번호 : " + article.getId());
            System.out.println("제목 : " + article.getTitle());
            System.out.println("==================");
        }
    }

    public static int findIndexById(int id) {
        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);
            if (article.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        return formattedDate;
    }

    public static void makeTestData(){
        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, getCurrentDateTime());
        Article a2 = new Article(2, "자바 질문좀 할게요~", "냉무", 0, getCurrentDateTime());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 0, getCurrentDateTime());
        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);
    }
}
package kr.or.ddit.paging;

import kr.or.ddit.vo.PaginationInfo;

public class BootstrapPaginationRenderer implements PaginationRenderer {

   private final String ULPATTERN = "<ul class='pagination'>%s</ul>";
   private final String PATTERN = "<li class='page-item %3$s'><a class='page-link' href='javascript:;' onclick='fn_paging(%1$d);'>%2$s</a>";

   @Override
   public String renderPagination(PaginationInfo<?> paging) {
      int startPage = paging.getStartPage();
      int endPage = paging.getEndPage();
      int totalPage = paging.getTotalPage();
      int currentPage = paging.getCurrentPage();
      
      StringBuffer html = new StringBuffer();
      
      if(startPage > 1) {
         html.append(
            String.format(PATTERN, startPage-1, "이전", "")
         );
      }else {
         html.append(
            String.format(PATTERN, startPage-1, "이전", "disabled")
         );
      }
      
      for(int page = startPage; page <= endPage; page++) {
         if(page == currentPage) {
            html.append(
               String.format(PATTERN, page, page, "active")
            );
         }else {
            html.append(
               String.format(PATTERN, page, page, "")
            );
         }
      }
      
      if(endPage < totalPage) {
         html.append(
            String.format(PATTERN, endPage+1, "다음", "")
         );
      }else {
         html.append(
            String.format(PATTERN, startPage-1, "이전", "disabled")
         );
      }
      
      
      return String.format(ULPATTERN, html);
   }
   /*
   <nav aria-label="...">
     <ul class="pagination">
       <li class="page-item disabled">
         <a class="page-link">Previous</a>
       </li>
       <li class='page-item'><a class='page-link' href='#'>1</a></li>
       <li class="page-item active" aria-current="page">
         <a class="page-link" href="#">2</a>
       </li>
       <li class="page-item"><a class="page-link" href="#">3</a></li>
       <li class="page-item">
         <a class="page-link" href="#">Next</a>
       </li>
     </ul>
   </nav>
   */
}
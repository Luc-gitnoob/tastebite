import {
  Pagination,
  PaginationContent,
  PaginationItem,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination";
import { generatePaginationLinks } from "./generate-pages";

type PaginatorProps = {
  currentPage: number;
  totalPages: number;
  onPageChange: (pageNumber: number) => void;
  showPreviousNext: boolean;
};

export default function Paginator({
  currentPage,
  totalPages,
  onPageChange,
  showPreviousNext,
}: PaginatorProps) {
  return (
    <Pagination>
      <PaginationContent>
        {showPreviousNext && totalPages > 1 && (
          <PaginationItem key="previous">
            <PaginationPrevious
              onClick={() => onPageChange(currentPage - 1)}
              disabled={currentPage <= 1}
            />
          </PaginationItem>
        )}
        {generatePaginationLinks(currentPage, totalPages, onPageChange).map(
          (link, index) => (
            <PaginationItem key={index}>{link}</PaginationItem>
          )
        )}
        {showPreviousNext && totalPages > 1 && (
          <PaginationItem key="next">
            <PaginationNext
              onClick={() => onPageChange(currentPage + 1)}
              disabled={currentPage >= totalPages}
            />
          </PaginationItem>
        )}
      </PaginationContent>
    </Pagination>
  );
}

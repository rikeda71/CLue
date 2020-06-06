export type PaperType = {
  paperId: number;
  title: string;
  conference: string;
  introduction?: string;
  year: number;
  task?: string;
  predicted?: boolean;
  url: string;
  label?: string;
  lang?: string;
  authors?: Array<AuthorType>;
};

export type AuthorType = {
  authorId: number;
  name: string;
  papers?: Array<PaperType>;
};

export type PapersType = {
  papers: Array<PaperType>;
};

export type PaperPageProps = {
  papers?: Array<PaperType>;
};

export type PaperProps = {
  title: string;
  conference: string;
  year: number;
  task?: string;
  predicted?: boolean;
  url: string;
};

export type PapersProps = {
  papers: Array<PaperProps>;
};

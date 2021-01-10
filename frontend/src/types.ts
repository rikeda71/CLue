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

export type PaperSearchConditionType = {
  year?: number;
  task?: string;
  title?: string;
  introduction?: string;
  lang?: string;
  conference?: string;
  limit?: number;
  offset?: number;
};

export type ConferenceType = {
  conferenceId: number;
  conferenceName: string;
};

export type YearType = {
  yearId: number;
  year: number;
};

export type TaskType = {
  taskId: number;
  taskName: string;
  taskLabelName: string;
};

export type UserType = {
  id: number;
  email: string;
};
